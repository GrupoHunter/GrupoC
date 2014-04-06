package ar.edu.unq.desapp

class LibrarySystem {
	var books: List[(Book, Int)] = List()
	var users: List[User] = List()
		
	def manualBookLoad(aBook: Book) 
	
	def containsBook(anIsbn: String): Boolean
	
	def changeAmount(anIsbn: String, amount: Int)
	
	def editBook(anIsbn: String): Book
	
	def removeBook(anIsbn: String)
}