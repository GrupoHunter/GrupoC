package ar.edu.unq.desapp

import java.util.ArrayList

class LibrarySystem {
    var books: List[Book] = List()
	var users: List[User] = List()
	
	def manualBookLoad(aBook: Book, amount: Int = 0){
		books = aBook :: books
		if(amount > 1){
		  this.changeAmount(aBook.isbn, amount)
		}
    }
	
	def automaticBookLoadByIsbn(isbn: String){
		//TODO
	}
	
	def automaticBookLoadByTitle(title: String){
		//TODO
	}
	
	def removeBook(anIsbn: String){
		val book = getBookByIsbn(anIsbn)
		books filter (b => b.equals(book.get))
	}
	
	def changeAmount(anIsbn: String, amount: Int){
		getBookByIsbn(anIsbn).get.amount += amount
	}
	
	def containsBook(anIsbn: String): Boolean = {
		val book = getBookByIsbn(anIsbn).get
		books.contains(book)
	}
	
	def getBookByIsbn(anIsbn: String): Option[Book] = {
		var result: List[Book] = books filter (b => (b.isbn == anIsbn))
		if(result.isEmpty){
		  None
		}else{
		  Some(result.head)
		}
	}
}