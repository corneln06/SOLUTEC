namespace PaymentService.Api.Model;

public class Invoice
{
    public DateOnly Date { get; set; }
    public Installment InstallmentId { get; set; }
}