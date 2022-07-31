package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric","Evans");
        Book ddd = new Book("Domain Driven Design","123123");
        Publisher elad = new Publisher("Elad","Street","Jerusalem","Israel","11111");

        publisherRepository.save(elad);

        System.out.println("Publisher count: " + publisherRepository.count());

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(elad);
        elad.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(elad);

        Author rod = new Author("Rod","Johnson");
        Book noEJB = new Book("J@EE Dev withour EJB","3939459459");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(elad);
        elad.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(elad);


        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher Number of books: " +elad.getBooks().size());
    }
}
