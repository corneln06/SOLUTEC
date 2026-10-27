using System.ComponentModel.DataAnnotations;

namespace Solutec.Frontend.Models;

public class LoginRequest
{
    [Required(ErrorMessage = "Username is required")]
    public string UserName { get; set; } = "";

    [Required(ErrorMessage = "Password is required")]
    public string Password { get; set; } = "";
}