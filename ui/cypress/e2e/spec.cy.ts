describe('My First Test', () => {
  it('Visits the initial project page', () => {
    cy.visit('/')
    cy.contains('Welcome')
    cy.contains('sandbox app is running!')
  })
})

// describe('Product Inventory', () => {
//   beforeEach(() => {
//     cy.visit('/')
//   });

//   it('Check Products in the inventory', () => {
//     cy.visit('dashboard')
//     cy.
//   });
// });
