import {
  useGetAllProductsQuery,
} from "../../slice/productApiSlice";
import Product from "./Product";
import "../../css/button.css"

const Home = () => {
  const { data } = useGetAllProductsQuery();
  
  // const imageUrl = URL.createObjectURL(imageBlob);
  return (
    <div className="d-flex flex-row flex-wrap scrollbarHide">
      {data && data.map((product) => <Product product={product} />)}
    </div>
  );
};

export default Home;
