namespace Solutec.Frontend.Models;

// what the backend sends back on successful login
public record LoginResponse(string UserName, string? Role);