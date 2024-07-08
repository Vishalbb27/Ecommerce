import React from "react";
import { GoHomeFill } from "react-icons/go";
import { FaShoppingCart } from "react-icons/fa";
import { FaTruck } from "react-icons/fa";
import { Link } from "react-router-dom";
import "../../../css/input.css";
const Sidebar = () => {
  return (
    <div className="d-flex flex-column h-50 me-5">
      <div className="d-flex flex-row mb-2">
        <GoHomeFill size={25} />
        <p className="ps-3">
          <Link to="" className="text-dark" style={{ textDecoration: "none" }}>
            Home
          </Link>
        </p>
      </div>
      <div className="d-flex flex-row mb-2 mt-2">
        <FaShoppingCart size={25} />
        <p className="ps-3">
          <Link to="/cart" className="text-dark" style={{ textDecoration: "none" }}>
            Cart
          </Link>
        </p>
      </div>
      <div className="d-flex flex-row mt-2">
        <FaTruck size={25} />
        <p className="ps-3">
          <Link to="/orders" className="text-dark" style={{ textDecoration: "none" }}>
            Orders
          </Link>
        </p>
      </div>
    </div>
  );
};

export default Sidebar;
