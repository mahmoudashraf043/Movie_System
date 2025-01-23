export class MovieOMDBAPI {
    Title: string;
    Year: string;
    Genre: string;
    Director: string;
    Plot: string;
    imdbID: string;
    Poster: string;

    constructor(
      Title: string,
      Year: string,
      Genre: string,
      Director: string,
      Plot: string,
      imdbID:string,
      Poster: string
    ) {
      this.Title = Title;
      this.Year = Year;
      this.Genre = Genre;
      this.Director = Director;
      this.imdbID = imdbID;
      this.Plot = Plot;
      this.Poster = Poster;
    }
  }
