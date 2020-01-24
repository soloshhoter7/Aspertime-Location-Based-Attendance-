import { Employee } from './Register.model';

export class attendance{
    public serial:number;
	public employee:Employee;
	public punchIn:string='';
	public punchOut:string='';
	public totaltime:number;
	public date:string;
	public Attendance:string;

}