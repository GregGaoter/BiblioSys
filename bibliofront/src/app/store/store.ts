import { Action, configureStore, ThunkAction } from "@reduxjs/toolkit";
import bibliothequeReducer from "../store/slice/BibliothequeSlice";
import livreReducer from "../store/slice/LivreSlice";
import rayonReducer from "../store/slice/RayonSlice";
import genreReducer from "../store/slice/GenreSlice";
import auteurReducer from "../store/slice/AuteurSlice";
import livreResultatReducer from "../store/slice/LivreResultatSlice";
import collectionReducer from "../store/slice/CollectionSlice";

export const store = configureStore({
  reducer: {
    bibliotheque: bibliothequeReducer,
    livre: livreReducer,
    rayon: rayonReducer,
    genre: genreReducer,
    auteur: auteurReducer,
    livreResultat: livreResultatReducer,
    collection: collectionReducer,
  },
  middleware: (getDefaultMiddleware) => getDefaultMiddleware({ serializableCheck: false }),
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
export type AppThunk = ThunkAction<void, RootState, unknown, Action>;
