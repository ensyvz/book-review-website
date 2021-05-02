package ekebookreview;

public class Book {

    private int id;
    private String name;
    private String author;
    private String publisher;
    private String synopsis;
    private float rating;
    
    public Book(int id, String name, String author, String publisher, String synopsis, float rating) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.synopsis = synopsis;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    
    
}
