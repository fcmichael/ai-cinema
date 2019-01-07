import {Genre} from "../movie/genre";
import {Country} from "../movie/contry";
import {Show} from "../movie/show";

export class ProgrammeMovie {
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
