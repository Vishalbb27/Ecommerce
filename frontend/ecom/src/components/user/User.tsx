import React from "react";
import Sidebar from "./navigation/Sidebar";
import Header from "./navigation/Header";
import { Outlet } from "react-router-dom";
import "../../css/containerSize.css";

const User = () => {
  return (
    <div className="app-container">
      <div
        className="header"
        style={{ position: "fixed", width: "100%", backgroundColor: "white" }}
      >
        <Header />
      </div>
      <div className="d-flex main-content " style={{ marginTop: "58px" }}>
        <div
          style={{
            height: "100vh",
            position: "fixed",
            backgroundColor: "white",
          }}
          className="shadow p-4 pe-5"
        >
          <Sidebar />
        </div>
        <div className="mt-4" style={{ marginLeft: "280px" }}>
          <Outlet />
        </div>
      </div>
    </div>
  );
};

export default User;
