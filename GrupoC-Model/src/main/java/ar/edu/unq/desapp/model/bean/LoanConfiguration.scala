package ar.edu.unq.desapp.model.bean

import javax.persistence._
import scala.beans.BeanProperty

@Entity
@Table(name = "loan_config")
class LoanConfiguration {
  
  @Id @GeneratedValue
  var id: Int = _
  
  @BeanProperty
  var maxDaysOfLoan: Int = 4
  @BeanProperty
  var amountAllowLoan: Int = 3
}
