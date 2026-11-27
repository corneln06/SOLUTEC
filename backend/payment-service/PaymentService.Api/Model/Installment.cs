namespace PaymentService.Api.Model;

public class Installment
{
    public long Id { get; set; }
    public string Method { get; set; }
    public Boolean IsPaid { get; set; }
    
    public decimal InterestAmount { get; set; }
        
    public DateOnly DueDate { get; set; }
    public DateOnly? PaidDate { get; set; }
}