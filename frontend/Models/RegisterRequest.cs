using System.ComponentModel.DataAnnotations;

namespace Solutec.Frontend.Models;

public class RegisterRequest
{
    [Required(ErrorMessage = "Username is required")]
    [StringLength(50, MinimumLength = 3, ErrorMessage = "Username must be 3-50 characters")]
    public string UserName { get; set; } = "";

    [Required(ErrorMessage = "Password is required")]
    [StringLength(100, MinimumLength = 8, ErrorMessage = "Password must be at least 8 characters")]
    public string Password { get; set; } = "";

    [Required(ErrorMessage = "Please confirm your password")]
    [Compare(nameof(Password), ErrorMessage = "Passwords do not match")]
    public string ConfirmPassword { get; set; } = "";
}