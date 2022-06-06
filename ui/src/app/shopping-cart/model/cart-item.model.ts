import { ProductId } from "src/app/product-inventory/model/product-id.model";

export interface CartItem {
    productId: string;
    quantity: number;
}