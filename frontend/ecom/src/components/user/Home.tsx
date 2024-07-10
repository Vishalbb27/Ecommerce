import { useGetAllProductsQuery } from "../../slice/productApiSlice";
import Product from "./Product";
import "../../css/button.css";
import { useGetCartByUserIdQuery } from "../../slice/cartApiSlice";

const Home = () => {
  const { data } = useGetAllProductsQuery();
  const { data: cart } = useGetCartByUserIdQuery(1);
  console.log(cart);
  // const imageUrl = URL.createObjectURL(imageBlob);
  return (
    <>
      <div className="d-flex flex-row flex-wrap scrollbarHide">
        {data && data.map((product) => <Product product={product} cart={6} />)}
      </div>
    </>
  );
};

export default Home;
