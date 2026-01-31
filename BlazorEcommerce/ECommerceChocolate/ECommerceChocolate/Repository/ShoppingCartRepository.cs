using ECommerceChocolate.Repository.Abstractions;
using ECommerceChocolate.Data;
using Microsoft.EntityFrameworkCore;

namespace ECommerceChocolate.Repository
{
    public class ShoppingCartRepository : IShoppingCartRepository
    {
        private readonly ApplicationDbContext _db;
        public ShoppingCartRepository(ApplicationDbContext dbContext)
        {
            _db = dbContext;
        }

        public async Task<bool> ClearCartAsync(string? userId)
        {
            var cartItems = await _db.ShoppingCarts.Where(u => u.UserId == userId).ToListAsync();
            _db.ShoppingCarts.RemoveRange(cartItems);
            return await _db.SaveChangesAsync() > 0;
        }

        public async Task<IEnumerable<ShoppingCart>> GetAllAsync(string? userId)
        {
            return await _db.ShoppingCarts.Where(u => u.UserId == userId).Include(u => u.Product).ToListAsync();
        }

        public async Task<bool> UpdateCartAsync(string userId, int productId, int updateBy)
        {
            if (string.IsNullOrEmpty(userId)) 
            { 
                return false; 
            }

            var cart = await _db.ShoppingCarts.FirstOrDefaultAsync(u => u.UserId == userId && u.ProductId == productId);
            if(cart == null)
            {
                cart = new ShoppingCart()
                {
                    UserId = userId,
                    ProductId = productId,
                    Quantity = updateBy
                };
                await _db.ShoppingCarts.AddAsync(cart);

            }
            else
            {
                cart.Quantity += updateBy;
                if(cart.Quantity <= 0)
                {
                    _db.ShoppingCarts.Remove(cart);
                    return await _db.SaveChangesAsync() > 0;
                }
            }
            return await _db.SaveChangesAsync() > 0;

        }
    } 
}
