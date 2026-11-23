DotNetEnv.Env.Load();
var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddControllers();
// Learn more about configuring OpenAPI at https://aka.ms/aspnet/openapi
builder.Services.AddOpenApi();

var app = builder.Build();
var connStr = builder.Configuration.GetConnectionString("PaymentDB");
var coreAddress = builder.Configuration["CoreGrpc:Address"];
// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.MapOpenApi();
}
Console.WriteLine($"Conn string loaded: {(connStr is null ? "NO" : "YES")}");
app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
