export class Movie {
  constructor(
    public id: number,
    public title: string,
    public genre: string,
    public ageLimit: string,
    public duration: number,
    public releaseYear: number,
    public description: string,
    public image: any
  ) {
  }
}
