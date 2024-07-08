import React from "react";
import {
  productDetails,
  useGetProductImageQuery,
} from "../../slice/productApiSlice";
import { IoStar } from "react-icons/io5";
import Button from "../UI/Button";
import { MdOutlineShoppingCart } from "react-icons/md";
import "../../css/button.css";

export type ProductProps = {
  product: productDetails;
};

const Product = ({ product }: ProductProps) => {
  const { data: imageBlob, isLoading } = useGetProductImageQuery(
    product.productId
  );
  return (
    <div className="d-flex flex-column me-5 shadow p-4 mb-4 rounded-4 scrollbarHide">
      <div className="text-center mb-3">
        {!isLoading && (
          <img
            src={`data:image/jpeg;base64,${imageBlob.image}`}
            alt="product"
            style={{ height: "100px", width: "100%" }}
            className="shadow"
          />
        )}
      </div>
      <div className="mb-2">
        <p className="p-0 m-0 text-capitalize h5 mb-1">{product.name}</p>
        <p className="p-0 m-0 h6 mb-1">Dual Shock</p>
        <p className="p-0 m-0 text-capitalize" style={{fontSize:"15px"}}>{product.description}</p>
      </div>
      <div className="d-flex flex-row justify-content-between">
        <div className="d-flex flex-column">
          <p className="h4 m-0">
            ₹{" "}
            {(product.price - (product.price * product.discount) / 100).toFixed(
              0
            )}
          </p>
          <p className="discountClass">
            <span className="productPrice">₹ {product.price}</span> &nbsp;
            <span className="discount">{product.discount}% off</span>
          </p>
        </div>
        <div className=" d-flex flex-column">
          <p className="ratingClass m-0 d-flex flex-row justify-content-between align-items-center">
            <p className="m-0 mb-1">
              <IoStar />
            </p>
            <p className="m-0">{product.rating}</p>
          </p>
          <p className="m-0">3k reviews</p>
        </div>
      </div>
      <div>
        <Button className="viewButton shadow-sm">VIEW</Button>
        <Button className="cartButton shadow">
          <MdOutlineShoppingCart /> ADD
        </Button>
      </div>
    </div>
  );
};

export default Product;
