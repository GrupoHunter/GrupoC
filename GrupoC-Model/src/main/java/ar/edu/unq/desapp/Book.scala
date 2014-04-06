package ar.edu.unq.desapp

class Book (val title: String, val isbn: String, val editorial: String, val image: Image,val description: String, val authors: List[Author]){
}

class Author (val name: String, var books: List[Book]){
}

class Image {}