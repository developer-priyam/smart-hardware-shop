import { ProductDescription } from "./product-description.model";
import { ProductId } from "./product-id.model";

export interface Product {
    productId: ProductId;
    description: ProductDescription;
}