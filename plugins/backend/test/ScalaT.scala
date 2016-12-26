import java.util.concurrent.TimeUnit

import org.apache.commons.codec.binary.Base64

import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success}

/**
 * Created by zhangmeng on 16-12-9.
 */

object ScalaT {
  type CoffeeBeans = String
  type GroundCoffee = String

  case class Water(temperature: Int)

  type Milk = String
  type FrothedMilk = String
  type Espresso = String
  type Cappuccino = String

  case class GrindingException(msg: String) extends Exception(msg)
  case class FrothingException(msg: String) extends Exception(msg)
  case class WaterBoilingException(msg: String) extends Exception(msg)
  case class BrewingException(msg: String) extends Exception(msg)


  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.Future
  import scala.util.Random

  /** *
    研磨所需的咖啡豆
    加热一些水
    用研磨好的咖啡豆和热水制做一杯咖啡
    打奶泡
    结合咖啡和奶泡做成卡布奇诺
  */


  //研磨咖啡豆
  def grind(beans: CoffeeBeans): Future[GroundCoffee] = Future {
    println("start grinding...")
    Thread.sleep(Random.nextInt(2000))
    if(beans == "baked beans") throw GrindingException("are u joking? ")
    println("finished grinding")
    s"ground coffee of $beans"
  }

  //加热水
  def heatWater(water: Water): Future[Water] = Future {
    println("heating the water now")
    Thread.sleep(Random.nextInt(2000))
    println("hot, it's hot!")
    water.copy(temperature = 85)
    throw WaterBoilingException("boom!!")
  }

  //奶泡
  def frothMilk(milk: Milk): Future[FrothedMilk] = Future {
    println("milk frothing system engaged")
    Thread.sleep(Random.nextInt(2000))
    println("shutting down milk frothing system")
    s"frothed $milk"
  }

  //卡布奇诺
  def brew(coffee: GroundCoffee, heatedWater: Water): Future[Espresso] = Future {
    println("happy brewing :)")
    Thread.sleep(Random.nextInt(2000))
    println("it's brewed!")
    "espresso"
  }

  import concurrent.Promise
  case class TaxCut(reduction: Int)

  val taxcut = Promise[TaxCut]()

  val taxcut2: Promise[TaxCut] = Promise()

  val taxCutF: Future[TaxCut] = taxcut.future

  def main(args: Array[String]) = {
    val tempreatureOkay: Future[Boolean] = heatWater(Water(25)) map { water =>
      println("we're in the future")
      (80 to 85) contains (water.temperature)
    }
    tempreatureOkay.onComplete {
      case Success(v) => println(v)
      case Failure(ex) => ex.printStackTrace
    }

    TimeUnit.SECONDS.sleep(4)
  }

}

object Government {
  import concurrent.ExecutionContext.Implicits.global
  case class TaxCut(v: Int)
  def redeemCampaignPledge(): Future[TaxCut] = {
    val p = Promise[TaxCut]()
    Future {
      println("Starting the new legislative period.")
      Thread.sleep(2000)
      p.success(TaxCut(20))
      println("We reduced the taxes ! you must reelect us!!!! 1111")
    }
    p.future
  }

  def main(args: Array[String]) = {
    val taxCutF: Future[TaxCut] = Government.redeemCampaignPledge()
    println("Now that they're elected, let's see if they remeber their promises.")
    taxCutF.onComplete {
      case Success(TaxCut(reduction)) =>
        println(s"A miracle! They really cut our taxes by $reduction percentage points!")
      case Failure(ex) =>
        println(s"They broke their promises! Again! Because of a ${ex.getMessage}")
    }
    Thread.sleep(3000)
  }
}

