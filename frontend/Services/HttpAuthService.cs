using System.Net;
using System.Net.Http.Json;
using Solutec.Frontend.Models;

namespace Solutec.Frontend.Services;

public class HttpAuthService : IAuthService
{
    private const string LoginUrl = "api/v1/auth/login";
    private const string RegisterUrl = "api/v1/auth/sign-up";

    private readonly HttpClient http;

    public HttpAuthService(HttpClient http)
    {
        this.http = http;
    }

    public async Task<LoginResponse> LoginAsync(LoginRequest request)
    {
        // Backend expects GET /login?userName=...&password=...
        var url = $"{LoginUrl}?userName={Uri.EscapeDataString(request.UserName)}" +
                  $"&password={Uri.EscapeDataString(request.Password)}";

        var response = await SendAsync(() => http.GetAsync(url));

        if (response.StatusCode == HttpStatusCode.Unauthorized)
            throw new AuthException("Wrong username or password.");

        if (!response.IsSuccessStatusCode)
            throw new AuthException("Something went wrong. Try again later.");

        // Backend returns the whole User; we only read userName and role, the rest is ignored
        var user = await response.Content.ReadFromJsonAsync<LoginResponse>();
        if (user is null)
            throw new AuthException("The server sent an invalid response.");

        return user;
    }

    public async Task RegisterAsync(RegisterRequest request)
    {
        var body = new { request.UserName, request.Password };
        var response = await SendAsync(() => http.PostAsJsonAsync(RegisterUrl, body));

        if (!response.IsSuccessStatusCode)
            throw new AuthException("Something went wrong. Try again later.");
    }

    // Runs the request; turns "server is down" into a friendly message instead of a crash
    private static async Task<HttpResponseMessage> SendAsync(Func<Task<HttpResponseMessage>> request)
    {
        try
        {
            return await request();
        }
        catch (HttpRequestException)
        {
            throw new AuthException("Can't reach the server. Try again in a moment.");
        }
    }
}