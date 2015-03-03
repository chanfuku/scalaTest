package models
import scalikejdbc._

case class Hobby(id: Int, name: String)

object Hobby extends SQLSyntaxSupport[Hobby] {

  // default
  val conn: scala.sql.Connection = ConnectionPool.borrow()
  def apply(h: SyntaxProvider[Hobby])(rs: WrappedResultSet): Hobby = apply(h.resultName)(rs)

  def apply(h: ResultName[Hobby])(rs: WrappedResultSet): Hobby = new Hobby(
    id = rs.get(h.id),
    name = rs.get(h.name))

  val h = Hobby.syntax("h")

  def findAll()(implicit session: DBSession = autoSession): List[Hobby] = withSQL {
    select.from(Hobby as h)
      .orderBy(h.id)
  }.map(Hobby(h)).list.apply()
}