import { useSelector } from "react-redux";
import { Navigate, Outlet } from "react-router-dom";
// import Header from "../components/Header";
import { RootSlice } from "../store/store";

function BasicProtectedRoute() {
  const { accessToken } = useSelector((state: RootSlice) => state.login);
  return (
    <>
      {/* <Header /> */}
      {accessToken !== null ? <Outlet /> : <Navigate to="/login" replace />}
    </>
  );
}

export default BasicProtectedRoute;
