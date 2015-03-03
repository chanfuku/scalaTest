package controllers

import models._
import play.api.data.Forms._
import play.api.data._
import play.api.mvc._
import java.text._

object UserController extends Controller {

  /**ユーザ検索用Form*/
  val userForm = Form(
      mapping("kana"->text,"group"->text)(User.apply)(User.unapply))

  /**ユーザ登録用Form*/
  val userRegistForm = Form(
       mapping("name"->text,
               "mail"->text,
               "birthday"->date("yyyy,MM,d"),
               "hobby"->list(text)
        )(UserRegistForm.apply)(UserRegistForm.unapply))

  /**初期画面*/
  def entryInit = Action {
    val groupList = User.showAllGroup()
    val filledForm = userForm.fill(User("",""))
    val userList = List()
    val inputKana = ""
    val inputGroup = ""
    Ok(views.html.user.entry(filledForm,groupList,userList,0,inputKana,inputGroup))
  }
  
  /**ユーザー検索*/
  def entrySubmit() = Action { implicit request =>
    val groupList = User.showAllGroup()
    val user = userForm.bindFromRequest.get
    val inputKana = user.kana
    val inputGroup = user.group
    val userList = UserInfo.getUsers(user)

  //productArity: Int　だと個数を正しく取得してくれないのでforで取得する
    var size = 0
    for(user <- userList){
      size = size + 1
    }

    Ok(views.html.user.entry(userForm,groupList,userList,size,inputKana,inputGroup))
  }

  /**ユーザ登録初期画面*/
  def input() = Action {
    println("init")
    val hobbyList : List[Hobby] = Hobby.findAll()
    for(hobby <- hobbyList){
      println(hobby.name)
    }
    Ok(views.html.regist.regist(userRegistForm,hobbyList))
  }
}