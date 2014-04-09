package ar.edu.unq.desapp.builderTest

import java.awt.Image
import ar.edu.unq.desapp._
import java.awt.image.BufferedImage

class BookBuilder {
  
	var title: String = "no title"
	var isbn : String = "no isbn"
	var editorial: String = "no editorial"
	var image: Image =  new BufferedImage(1,1,1)
	var description: String = "no description"
	var authors: List[Author] = List()
	var amount: Int = 0
	
	def aBook: BookBuilder = {
	  new BookBuilder()
	}
	
	def withTitle(aTitle: String): BookBuilder = {
	  title = aTitle
	  this
	}
	
	def withIsbn(anIsbn: String): BookBuilder = {
	  isbn = anIsbn
	  this
	}
	
	def withEditorial(anEditorial: String): BookBuilder = {
	  editorial = anEditorial
	  this
	}
	
	def withImage(anImage: Image): BookBuilder = {
	  image = anImage
	  this
	}
	
	def withDescription(anDescription: String): BookBuilder = {
	  description = anDescription
	  this
	}
	
	def withAmount(n: Int): BookBuilder = {
	  amount = n
	  this
	}
	
	def withAuthors(myAuthors: List[Author]): BookBuilder = {
	  authors = myAuthors
	  this
	}
	
	def build: Book = {
	  new Book(title, isbn, editorial, image, description, authors, amount)
	}
}
