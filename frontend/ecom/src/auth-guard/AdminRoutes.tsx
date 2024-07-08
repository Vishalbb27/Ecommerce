import { useSelector } from "react-redux";
import { Navigate, Outlet } from "react-router-dom";
import { RootSlice } from "../store/store";

function AdminRoutes() {
  const { role } = useSelector((state: RootSlice) => state.login);
  return (
    <>
      {role !== null && role === "ROLE_ADMIN" ? (
        <Outlet />
      ) : role !== null && role === "ROLE_USER" ? (
        <Navigate to="/" replace />
      ) : (
        <Navigate to="/register" replace />
      )}
    </>
  );
}

export default AdminRoutes;
