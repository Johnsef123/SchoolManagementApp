using System.ComponentModel.DataAnnotations;

namespace ECommerceChocolate.Data
{
    public class OrderContent
    {
        public int Id { get; set; }
        public int OrderDetailsId { get; set; }
        public OrderDetails orderDetails { get; set; }

        public int ProductId { get; set; }
        public Product product { get; set; }
        [Required]
        public int Quantity { get; set; }
        [Required]
        public double Price { get; set; }
        [Required]
        public string ProductName { get; set; }
    }
}
