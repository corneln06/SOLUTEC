using System.Net;
using System.Net.Http.Json;
using Solutec.Frontend.Models;

namespace Solutec.Frontend.Services;

public class HttpAuthService : IAuthService
{
    private const string LoginUrl = "api/auth/login";
    private const string RegisterUrl = "api/auth/register";

    private readonly HttpClient http;

    public HttpAuthService(HttpClient http)
    {
        this.http = http;
    }

    public async Task<LoginResponse> LoginAsync(LoginRequest request)
    {
        var body = new { request.UserName, request.Password };
        var response = await SendAsync(LoginUrl, body);

        if (response.StatusCode == HttpStatusCode.Unauthorized)
            throw new AuthException("Wrong username or password.");

        if (!response.IsSuccessStatusCode)
            throw new AuthException("Something went wrong. Try again later.");

        var user = await response.Content.ReadFromJsonAsync<LoginResponse>();
        if (user is null)
            throw new AuthException("The server sent an invalid response.");

        return user;
    }

    public async Task RegisterAsync(RegisterRequest request)
    {
        // only send what the backend needs
        var body = new { request.UserName, request.Password };
        var response = await SendAsync(RegisterUrl, body);

        if (response.StatusCode == HttpStatusCode.Conflict)
            throw new AuthException("This username is already taken.");

        if (response.StatusCode == HttpStatusCode.BadRequest)
            throw new AuthException("Some of the details are invalid.");

        if (!response.IsSuccessStatusCode)
            throw new AuthException("Something went wrong. Try again later.");
    }

    // sends the post, announces the user when server is down
    private async Task<HttpResponseMessage> SendAsync(string url, object body)
    {
        try
        {
            return await http.PostAsJsonAsync(url, body);
        }
        catch (HttpRequestException)
        {
            throw new AuthException("Can't reach the server. Try again in a moment.");
        }
    }
}