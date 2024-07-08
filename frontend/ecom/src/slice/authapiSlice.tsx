import { Login } from "../components/auth/LoginForm";
import { RegisterProps } from "../components/auth/RegisterForm";
import { AUTH_URL } from "../constants/constants";
import { apiSlice } from "./apiSlice";

export type Token = {
  accessToken: String | null;
};

export type LoginDetials = Token & {
  role: String | null;
  tokenType: String | null;
};

export const authapiSlice = apiSlice.injectEndpoints({
  endpoints: (builder) => ({
    login: builder.mutation<LoginDetials, Login>({
      query: ({ name: usernameOrEmail, password }) => ({
        url: `${AUTH_URL}/login`,
        method: "POST",
        body: { usernameOrEmail, password },
      }),
    }),
    register: builder.mutation<void, RegisterProps>({
      query: (data) => ({
        url: `${AUTH_URL}/register`,
        method: "POST",
        body: data,
      }),
    }),
    logout: builder.query<void, void>({
      query: () => ({
        url: `${AUTH_URL}/logout`,
        method: "GET",
      }),
    }),
  }),
});

export const {
  useLoginMutation,
  useLazyLogoutQuery,
  useLogoutQuery,
  useRegisterMutation,
} = authapiSlice;
