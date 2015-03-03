package models

import anorm.SqlParser._
import anorm._
import play.api.Play.current
import play.api.db._

case class User(kana:String, group:String)
case class UserInfo(name:String, kana:String, mail:String, area:String, group:String, date:String)

object User {

  def showAllGroup() : List[String] =
    DB.withConnection { implicit c =>
      val list: List[String] = SQL("SELECT name FROM groups").as(scalar[String].*)
      list
    }
}

object UserInfo {

  def getUsers(user:User) : List[(String,String,String,String,String,java.util.Date)] =
    DB.withConnection { implicit c =>
      if(user.kana != null && user.group != "empty") {
        val query: String = "SELECT m.name as name, m.name_kana as kana,m.mail_address as mail,m.area as area,g.name as group,m.join_date::date as date FROM member as m INNER JOIN groups as g ON m.group_id = g.id WHERE m.name_kana LIKE {kana} AND g.name = {group} ORDER BY m.id ASC"
        val userKana = "%" + user.kana + "%"
        val list = SQL(query).on("kana" -> userKana, "group" -> user.group).as(str("name") ~ str("kana") ~ str("mail") ~ str("area") ~ str("group") ~ date("date")  map (flatten) *)
        list
      } else {
        val query: String = "SELECT m.name as name, m.name_kana as kana,m.mail_address as mail,m.area as area,g.name as group, m.join_date::date as date FROM member as m INNER JOIN groups as g ON m.group_id = g.id"
        val list = SQL(query).as(str("name") ~ str("kana") ~ str("mail") ~ str("area") ~ str("group") ~ date("date") map (flatten) *)
        list
      }
    }
}