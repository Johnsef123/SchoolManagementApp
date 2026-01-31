using System.ComponentModel.DataAnnotations;

namespace ECommerceChocolate.Data
{
    public class OrderDetails
    {
        public int Id { get; set; }
        public string UserId { get; set; }

        [Required]
        [Display(Name="Order Total")]
        public double OrderTotal { get; set; }

        [Required]
        public string Status { get; set; }

        [Required]
        [Display(Name="Name")]
        public string Name { get; set; }

        [Required]
        [Display(Name="Phone Number")]
        public string PhoneNumber { get; set; }

        [Required]
        [Display(Name="Email Address")]
        public string Email { get; set; }

        public ICollection<OrderContent> orderContents { get; set; } = new List<OrderContent>();
    }
}
