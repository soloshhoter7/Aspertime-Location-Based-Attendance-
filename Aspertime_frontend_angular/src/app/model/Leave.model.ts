export class LeaveModel{
     public LeaveId:number;
     public empId:number;
     public LeaveSubject:String;
     public LeaveType:String="A";
     public Date:Date;
     public LeaveReason:String;
     public ApprovalStatus:String="P";
     public DateInString:string;
     public CancellationStatus:string="N";
}