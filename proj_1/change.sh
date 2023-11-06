for f in 50_zad*.txt; do
  new_name=$(echo "$f" | sed 's/50_zad/50_2_zad/')
  mv "$f" "$new_name"
done
