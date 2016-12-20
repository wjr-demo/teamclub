package models

import javax.persistence.{Transient, Entity, Id}

import com.avaje.ebean.annotation.Formula
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import play.db.ebean.Model
import play.db.ebean.Model.Finder
import play.libs.{Scala, Json}

import scala.beans.BeanProperty
import scala.collection.mutable.ListBuffer

/**
 * Created by zhangmeng on 16-9-6.
 */
@Entity
class AppFuncTree extends Model {

  @Id
  @BeanProperty
  @JsonProperty("id")
  var id: Int = _

  @BeanProperty
  @JsonProperty("text")
  var name: String = _

  @BeanProperty
  var appId: String = _

  @BeanProperty
  var module: String = _

  @BeanProperty
  var parent: Integer = _

  @Transient
  @BeanProperty
  @JsonProperty("nodes")
  var subTrees: java.util.List[AppFuncTree] = new java.util.ArrayList[AppFuncTree]

  @Transient
  @BeanProperty
  var state: State = _
}
class State {
  @BeanProperty
  var checked: Boolean = _
}

object AppFuncTree {
  val finder = new Finder(classOf[Int], classOf[AppFuncTree])

  def toJson(trees: java.util.List[AppFuncTree]): JsonNode = {
    val newTrees = Scala.toSeq(trees)
    val parents = newTrees.filter(s => {s.getParent == null || s.getParent == 0})
    for(p <- parents) {
      for(single <- newTrees) {
        if(single.getParent == p.getId){
          p.subTrees.add(single)
        }
      }
    }
    Json.toJson(Scala.asJava(parents))
  }
}