namespace PaymentService.Api.Model;

public class PaymentPlan
{
    public long Id { get; set; }
    public long ProjectId { get; set; }
    public decimal BudgetWithoutTax { get; set; }
    public decimal TaxRate { get; set; } = 0.21m;

    public decimal Budget => BudgetWithoutTax * (1 + TaxRate);

    public List<Installment> Installments { get; set; } = new();
    
}