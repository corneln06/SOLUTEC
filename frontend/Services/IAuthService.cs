using Solutec.Frontend.Models;

namespace Solutec.Frontend.Services;

public interface IAuthService
{
    Task<LoginResponse> LoginAsync(LoginRequest request);
    Task RegisterAsync(RegisterRequest request);
}