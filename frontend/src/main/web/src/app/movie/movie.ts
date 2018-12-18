import {Genre} from "./genre";
import {Country} from "./contry";
import {Show} from "./show";

export class Movie {
  constructor(
    public id: number,
    public title: string,
    public genre: Genre,
    public ageLimit: string,
    public duration: number,
    public releaseYear: number,
    public country: Country,
    public description: string,
    public image: any,
    public shows: Show[]
  ) {
  }
}
