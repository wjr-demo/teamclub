package router

import java.io.File

import controllers.Assets
import play.api.{Logger, Routes}
import play.api.mvc.{Handler, RequestHeader}
import play.core.Router.{HandlerDef, Param, Route}
import play.core.{DynamicPart, PathPattern, Router, StaticPart}
import play.router.RoutesCompiler.{RoutesCompilationError, RouteFileParser}

import scala.collection.immutable.HashMap
import scala.collection.mutable
import scala.util.matching.Regex

/**
 * Created by zhangmeng on 16-6-30.
 */
object DynamicRoutes extends Router.Routes{

  private var _prefix = "/"

  val routMap = new mutable.HashMap[String, PartialFunction[RequestHeader, Handler]] with mutable.SynchronizedMap[String, PartialFunction[RequestHeader, Handler]]
  val prefixHandlerMap = new mutable.HashMap[String, (RequestHeader) => Option[Handler]] with mutable.SynchronizedMap[String, (RequestHeader) => Option[Handler]]

  var documentation: Seq[(String, String, String)] = scala.collection.mutable.ArrayBuffer()

  def setPrefix(prefix: String) = {_prefix = prefix}

  def prefix = _prefix

  lazy val defaultPrefix = { if(prefix.endsWith("/")) "" else "/" }

  lazy val controllers_Assets_at = Route("GET", PathPattern(List(StaticPart(prefix),StaticPart(defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))

  lazy val defaultRoutes: PartialFunction[RequestHeader, Handler] = {
    case controllers_Assets_at(params) => {
      call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", prefix + """assets/$file<.+>"""))
      }
    }
  }

  lazy val regex = """/(.*?)/.+""".r

  val routes: PartialFunction[RequestHeader, Handler] = defaultRoutes

  override def handlerFor(request: RequestHeader): Option[Handler] = {
    request.uri match {
      case regex(rgx) => {
        prefixHandlerMap.get(rgx) match {
          case Some(v) => {
            val resp = v.apply(request)
            resp match {
              case Some(v) => resp
              case None => {
                val handler = routMap.get(rgx).getOrElse(routes).lift(request)
                handler
              }
            }
          }
          case None => {
            val handler = routMap.get(rgx).getOrElse(routes).lift(request)
            handler
          }
        }
      }
      case _ => routes.lift(request)
    }
  }



  def appendRoutes(prefix: String, router:Router.Routes, prefixHandler: Option[(RequestHeader) => Option[Handler]]) = this.synchronized {
    router.setPrefix("/" + prefix)
    routMap += (prefix -> router.routes)
    prefixHandler.map(v => {
      prefixHandlerMap += (prefix -> v)
    })
    documentation ++= router.documentation
  }

}