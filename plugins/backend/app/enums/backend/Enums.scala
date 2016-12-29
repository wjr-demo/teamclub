package enums.backend

import com.fasterxml.jackson.databind.JsonNode
import com.google.common.collect.{Lists, ImmutableMap}
import libs.backend.Permissons._
import play.libs.{Scala, Json}

/**
 * Created by zhangmeng on 16-12-29.
 */
object Planet extends Enumeration {
  case class PlanetVal(name: String, mass: Double, radius: Double) extends Val(name) {
    val G = 6.67300E-11
    val surfaceGravity = G * mass / (radius * radius)
    def surfaceWeight(otherMass: Double) = otherMass * surfaceGravity
  }
  val MERCURY = PlanetVal("Mercury", 3.303e+23, 2.4397e6)
  val VENUS   = PlanetVal("Venus",   4.869e+24, 6.0518e6)
  val EARTH   = PlanetVal("Earth",   5.976e+24, 6.37814e6)

  implicit def valueToPlanet(v:Value): PlanetVal = v.asInstanceOf[PlanetVal]
}

object Department extends Enumeration {
  case class DepartmentVal(key: String, desc: String) extends Val(key)
  val ADMINISTRATOR = DepartmentVal("ADMINISTRATOR", "行政部")
  val FINANCE = DepartmentVal("FINANCE", "财务部")

  implicit def valueToDepartment(v:Value): DepartmentVal = v.asInstanceOf[DepartmentVal]

  def getAll():JsonNode = {
    val list = Lists.newArrayList[ImmutableMap[String, String]]()
    Department.values foreach{ p =>
      val map = ImmutableMap.of("id", p.key, "name", p.desc)
      list.add(map)
    }
    Json.toJson(list)
  }
}

object EnumMain {
  def main(args: Array[String]): Unit = {
    println(Department.getAll())
  }
}