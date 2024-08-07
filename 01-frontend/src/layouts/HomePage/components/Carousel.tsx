import { ReturnBook } from "./ReturnBook";
import { useEffect, useState } from "react";
import BookModel from "../../../models/BookModel";
import { SpinnerLoading } from "../../Utils/SpinnerLoading";
import { Link } from "react-router-dom";

export const Carousel = () => {
  const [books, setBooks] = useState<BookModel[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [httpError, setHttpError] = useState<string | null>(null);

  useEffect(() => {
    const fetchBooks = async () => {
      const baseUrl: string = `${process.env.REACT_APP_API}/books`;

      const url: string = `${baseUrl}/?page=0&size=9`;

      try {
        const response = await fetch(url);

        if (!response.ok) {
          throw new Error("Something went wrong!");
        }

        const responseJson = await response.json();

        if (responseJson && responseJson._embedded && responseJson._embedded.books) {
          const responseData = responseJson._embedded.books;

          const loadedBooks: BookModel[] = responseData.map((book: any) => ({
            id: book.id,
            title: book.title,
            author: book.author,
            description: book.description,
            copies: book.copies,
            copiesAvailable: book.copiesAvailable,
            category: book.category,
            img: book.img,
          }));

          setBooks(loadedBooks);
        } else {
          throw new Error("No books found!");
        }
      } catch (error: any) {
        setHttpError(error.message);
      } finally {
        setIsLoading(false);
      }
    };
    fetchBooks();
  }, []);

  if (isLoading) {
    return (
      <div className="container m-5">
        <SpinnerLoading />
      </div>
    );
  }

  if (httpError) {
    return (
      <div className="container m-5">
        <p>{httpError}</p>
      </div>
    );
  }

  if (!books.length) {
    return (
      <div className="container m-5">
        <p>No books available</p>
      </div>
    );
  }

  return (
    <div className='container mt-5' style={{ height: 550 }}>
            <div className='homepage-carousel-title'>
                <h3>Find your next "I stayed up too late reading" book.</h3>
            </div>
            <div id='carouselExampleControls' className='carousel carousel-dark slide mt-5 
                d-none d-lg-block' data-bs-interval='false'>

                {/* Desktop */}
                <div className='carousel-inner'>
                    <div className='carousel-item active'>
                        <div className='row d-flex justify-content-center align-items-center'>
                            {books.slice(0, 3).map(book => (
                                <ReturnBook book={book} key ={book.id} />
                            ))}
                        </div>
                    </div>
                    <div className='carousel-item'>
                        <div className='row d-flex justify-content-center align-items-center'>
                            {books.slice(3, 6).map(book => (
                                <ReturnBook book={book} key ={book.id} />
                            ))}
                        </div>
                    </div>
                    <div className='carousel-item'>
                        <div className='row d-flex justify-content-center align-items-center'>
                            {books.slice(6, 9).map(book => (
                                <ReturnBook book={book} key ={book.id} />
                            ))}
                        </div>
                    </div>
                </div>
                <button className='carousel-control-prev' type='button'
                    data-bs-target='#carouselExampleControls' data-bs-slide='prev'>
                    <span className='carousel-control-prev-icon' aria-hidden='true'></span>
                    <span className='visually-hidden'>Previous</span>
                </button>
                <button className='carousel-control-next' type='button'
                    data-bs-target='#carouselExampleControls' data-bs-slide='next'>
                    <span className='carousel-control-next-icon' aria-hidden='true'></span>
                    <span className='visually-hidden'>Next</span>
                </button>
            </div>


      {/* Mobile */}
      <div className="d-lg-none mt-3">
        <div className="row d-flex justify-content-center align-items-center">
          {books[0] && <ReturnBook book={books[0]} key={books[0].id} />}
        </div>
      </div>
      <div className="homepage-carousel-title mt-3">
        <Link className="btn btn-outline-secondary btn-lg" to="/search">
          View More
        </Link>
      </div>
    </div>
  );
};
