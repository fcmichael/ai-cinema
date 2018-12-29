export class Show {
  constructor(
    public id: number,
    public movieTitle: string,
    public showDate: string,
    public showTime: string,
    public auditoriumName: string,
    public auditoriumRows: number,
    public auditoriumColumns: number,
    public reservedSeats: string[]
  ) {

  }
}
