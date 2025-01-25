export class movie{
    id:number;
    Title:string;
    Genre: string;
    Year:string;
    Plot:string;
    Poster:string;
    Director:string;
    imdbId:string;

  constructor(
      id:number,
      Title: string,
      Year: string,
      Genre: string,
      Director: string,
      Plot: string,
    imdbID:string,
    Poster: string
  )
{
  this.id = id;
  this.Title = Title;
  this.Year = Year;
  this.Genre = Genre;
  this.Director = Director;
  this.imdbId = imdbID;
  this.Plot = Plot;
  this.Poster = Poster;
}

}
