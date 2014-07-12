package ar.edu.unq.desapp.repository.bean

import ar.edu.unq.desapp.utils.builder.Builder
import org.scalatest.{FunSpec, GivenWhenThen, Matchers}
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration( Array("classpath:/META-INF/spring-persistence-context.xml", "classpath:/spring/*.xml"))
class HibernateDAOTest extends FunSpec with Matchers with GivenWhenThen with Builder

//{
//  new TestContextManager(classOf[HibernateDAOTest]).prepareTestInstance(this)
//  @Autowired
//  var bookRepository: BookRepository = _
//  @Autowired
//  var userRepository: UserRepository = _
//
//  describe("Hibernate BookRepository") {
//    it("should work all the functionality save and delete of the BookRepository") {
//
//      Given("four books i want load to data base")
//
//      val bookA = aBook
//      				.withTitle("Alguien que anda por ahi")
//            	.withIsbn("isbn-1231")
//      				.withEditorial("Editorial Argentina")
//      				.withDescription("Libro por Julio Cortazar").build
//      val bookB = aBook
//      				.withTitle("El alquimista")
//      				.withIsbn("isbn-234")
//      				.withEditorial("Editorial-A")
//      				.withDescription("Libro escrito por Pablo Cohelo").build
//      val bookC = aBook
//      				.withTitle("Cien años de soledad")
//      				.withIsbn("isbn-879")
//      				.withEditorial("Editorial-Colombia")
//      				.withDescription("Libro escrito por Gabriel Garcia Marquez").build
//      val bookD = aBook
//      				.withTitle("Moby Dick")
//      				.withIsbn("isbn-876")
//      				.withEditorial("Editorial-AAA")
//      				.withDescription("Libro que trata de ballenas :P").build
//
//      When("save the books")
//      bookRepository.save(bookA)
//      bookRepository.save(bookB)
//      bookRepository.save(bookC)
//      bookRepository.save(bookD)
//
//      Then("should get loaded the books")
//      val books_added: List[Book] = bookRepository.findAll
//
//      bookRepository.count should be(4)
//
//      books_added(0) should have(
//    		  			'title("Alguien que anda por ahi"),
//    		  			'isbn("isbn-1231"),
//    		  			'editorial("Editorial Argentina"),
//    		  			'description("Libro por Julio Cortazar")
//    		  			)
//      books_added(1) should have(
//    		  			'title("El alquimista"),
//    		  			'isbn("isbn-234"),
//    		  			'editorial("Editorial-A"),
//    		  			'description("Libro escrito por Pablo Cohelo")
//    		  			)
//      books_added(2) should have(
//    		  			'title("Cien años de soledad"),
//    		  			'isbn("isbn-879"),
//    		  			'editorial("Editorial-Colombia"),
//    		  			'description("Libro escrito por Gabriel Garcia Marquez")
//    		  			)
//      books_added(3) should have(
//    		  			'title("Moby Dick"),
//    		  			'isbn("isbn-876"),
//    		  			'editorial("Editorial-AAA"),
//    		  			'description("Libro que trata de ballenas :P")
//    		  			)
//
//      And("when delete two book")
//      bookRepository.delete(bookB)
//      bookRepository.delete(bookC)
//
//      Then("should have the bookA and bookD")
//      val book_not_delete = bookRepository.findAll
//
//      bookRepository.count should be(2)
//
//      book_not_delete(0) should have(
//    		  			'title("Alguien que anda por ahi"),
//    		  			'isbn("isbn-1231"),
//    		  			'editorial("Editorial Argentina"),
//    		  			'description("Libro por Julio Cortazar")
//    		  			)
//
//      book_not_delete(1) should have(
//    		  			'title("Moby Dick"),
//    		  			'isbn("isbn-876"),
//    		  			'editorial("Editorial-AAA"),
//    		  			'description("Libro que trata de ballenas :P")
//    		  			)
//
//    }
//
//    ignore("should save the authors to the book") {
//      Given("one book with one Author")
//      val author_bookA = anAuthor.withName("Julio Cortazar").build :: Nil
//      val bookA: Book = aBook.withAuthors(author_bookA).build
//
//      When("save the books with authors")
//      bookRepository.save(bookA)
//
//      Then("should get book with the author")
//      val bookA_saved = bookRepository.findByExample(bookA)(0)
//
//      bookA_saved should have('authors(author_bookA))
//    }
//
//    it("should modify a book") {
//      Given("one book with following details")
//      val book_to_save: Book = aBook.withTitle("Moby Dick").withIsbn("isbn-0123").build
//      bookRepository.save(book_to_save)
//
//      When("added more detail and save it")
//      val book_to_modify: Book = bookRepository.findByExample(book_to_save)(0)
//      book_to_modify.title = "Moby Dick, The whale"
//      book_to_modify.description = "Write by Herman Melville"
//      bookRepository.save(book_to_modify)
//
//     Then("should get following details modify")
//     val book_modified = bookRepository.findByExample(book_to_modify)(0)
//
//     book_modified should have(
//    		 			'title("Moby Dick, The whale"),
//    		 			'description("Write by Herman Melville"),
//    		 			'isbn("isbn-0123"))
//    }
//
//    it("loan book repository") {
//
//      val bookA = aBook
//        .withTitle("Alguien que anda por ahi")
//        .withIsbn("isbn-1231")
//        .withEditorial("Editorial Argentina")
//        .withDescription("Libro por Julio Cortazar").build
//      val bookB = aBook
//        .withTitle("El alquimista")
//        .withIsbn("isbn-234")
//        .withEditorial("Editorial-A")
//        .withDescription("Libro escrito por Pablo Cohelo").build
//
//
//      val userA = anUser.build
//      val userB = anUser.withName("asdasd").build
//      bookRepository.save(bookA)
//      bookRepository.save(bookB)
//      userRepository.save(userA)
//      userRepository.save(userB)
//
//      val loanBookA = new LoanBook(new DateTime, new DateTime)
//      loanBookA.book = bookA
//      loanBookA.user = userA
//      val loanBookB = new LoanBook(new DateTime, new DateTime)
//      loanBookB.book = bookB
//      loanBookB.user = userB
//      val loanBookC = new LoanBook(new DateTime, new DateTime)
//      loanBookC.book = bookA
//      loanBookC.user = userB
//
//      loanBookRepository.save(loanBookA)
//      loanBookRepository.save(loanBookB)
//      loanBookRepository.save(loanBookC)
//
//      val loanBooks: List[LoanBook] = loanBookRepository.findAll.toList
//      loanBooks.size should be (1)
//    }
//  }
//}
