namespace Solutec.Frontend.Services;

// thrown when login/register fails
public class AuthException : Exception
{
    public AuthException(string message) : base(message)
    {
    }
}