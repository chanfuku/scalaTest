package models

import scalikejdbc._,SQLInterpolation._

/**
 * Created by rks-user on 2015/02/04.
 */
case class UserRegistForm(name:String, mail:String, birthday:java.util.Date, hobby:List[String])

object UserRegist{
  
}

