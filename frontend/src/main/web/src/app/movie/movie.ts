import {Genre} from "./genre";
import {Country} from "./contry";

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
    public image: any
  ) {
  }
}
