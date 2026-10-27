using Solutec.Frontend.Components;
using Solutec.Frontend.Services;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddRazorComponents()
    .AddInteractiveServerComponents();

// Auth service, with an HttpClient pointed at the gateway
var gatewayUrl = builder.Configuration["Gateway:BaseUrl"]!;
builder.Services.AddHttpClient<IAuthService, HttpAuthService>(c =>
    c.BaseAddress = new Uri(gatewayUrl));

var app = builder.Build();

if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error", createScopeForErrors: true);
    app.UseHsts();
}
app.UseStatusCodePagesWithReExecute("/not-found", createScopeForStatusCodePages: true);
app.UseHttpsRedirection();

app.UseAntiforgery();

app.MapStaticAssets();
app.MapRazorComponents<App>()
    .AddInteractiveServerRenderMode();

app.Run();