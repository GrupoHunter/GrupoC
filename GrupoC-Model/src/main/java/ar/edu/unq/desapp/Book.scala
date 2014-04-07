package ar.edu.unq.desapp

import java.awt.Image
//import org.joda.time.DateTime

class Book(val title: String, val isbn: String,
			val editorial: String, var image: Image,
			var description: String, val authors: List[Author], 
			var amount: Int = 1/*, val registrationDate: DateTime = DateTime.now()*/)