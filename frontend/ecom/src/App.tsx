import BasicProtectedRoute from "./auth-guard/BasicProtectedRoute";
import LoginForm from "./components/auth/LoginForm";
import store from "./store/store";
import { Provider } from "react-redux";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import RegisterForm from "./components/auth/RegisterForm";
import { Flip, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import AdminRoutes from "./auth-guard/AdminRoutes";
import Home from "./components/user/Home";
import User from "./components/user/User";
import Cart from "./components/user/Cart";
import ProductDetails from "./components/user/ProductDetails";
import ShippingDetails from "./components/user/ShippingDetails";
import Payment from "./components/user/Payment";
import Confirmation from "./components/user/Confirmation";

function App() {
  return (
    <Provider store={store}>
      <BrowserRouter>
        <Routes>
          {/* <Route path="/" element={<Home />} /> */}
          <Route path="" element={<User />}>
            <Route path="/" element={<Home />} />
            <Route path="/cart" element={<Cart />} />
            <Route path="/product/:id" element={<ProductDetails />} />
            <Route path="/shipping" element={<ShippingDetails />} />
            <Route path="/payment" element={<Payment />} />
            <Route path="/confirmation" element={<Confirmation />} />
          </Route>
          <Route path="/login" element={<LoginForm />} />
          <Route path="/register" element={<RegisterForm />} />
        </Routes>
      </BrowserRouter>
      <ToastContainer
        position="bottom-center"
        autoClose={1000}
        hideProgressBar
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss={false}
        draggable
        pauseOnHover={false}
        theme="colored"
        transition={Flip}
      />
    </Provider>
  );
}

export default App;
