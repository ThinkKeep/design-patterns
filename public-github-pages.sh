#!/bin/sh
git branch -D gh-pages
git checkout --orpha gh-pages
gitbook build
git rm --cached -r .
git clean -df
rm -rf *~
echo "*~" > .gitignore
echo "_book" >> .gitignore
cp -r _book/* .
git add .
git commit -m "Publish book"
git push thinkkeep :gh-pages
git push -u thinkkeep gh-pages
